package com.data.data.service;

import com.data.data.entity.MountainInfo;
import com.data.data.entity.MountainInfoTemp;
import com.data.data.repository.MountainInfoRepository;
import com.data.data.repository.MountainInfoTempRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class MountainServiceImpl implements MountainService {
  private final MountainInfoRepository mountainInfoRepository;
  private final MountainInfoTempRepository mountainInfoTempRepository;
  @Value("${value.serviceKey}")
  String key;

  List<MountainInfoTemp> tempList;

  @Override
  public void getMountainInfo() {
    tempList = mountainInfoTempRepository.findAll();
    for (MountainInfoTemp infoTemp : tempList) {
      String name = infoTemp.getName().trim();
      if (infoTemp.getName().equals("남산(금오산)"))
        name = "금오산";
      ResponseEntity<String> response = callApi("http://api.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice?ServiceKey=" + key + "&mntnNm=" + name + "&numOfRows=1&pageNo=1");

      HttpStatus code = response.getStatusCode();

      MountainInfo mountain;

      if (code == HttpStatus.OK) {
        JsonObject jsonObject = JsonParser.parseString(Objects.requireNonNull(response.getBody())).getAsJsonObject();
//        log.info("jsonObject is : {}", jsonObject);

        JsonElement items = jsonObject.get("response").getAsJsonObject().get("body").getAsJsonObject().get("items");
//        log.info("items is : {}", items);

        if (items.toString().length() == 0 || items.toString().equals("\"\"")) {
          log.error("mountain name that fail to get data is : {}", infoTemp.getName());
          continue;
        }

        JsonElement body = items.getAsJsonObject().get("item");
//    log.info("body is : {}", body);

        String description = body.getAsJsonObject().get("mntninfodtlinfocont").toString().replace("<BR>", "\n").replace("</p>", "\n").replace("&lt;br /&gt;\r\n", "").replace("&lt;br /&gt;\r\n&lt;br /&gt;\r\n", "").replace("<p>", "\n").replace("\"", "");
//    log.info("description is : {}", description);

        String imgUrl = body.getAsJsonObject().get("mntnattchimageseq").toString().replace("\"", "");
//    log.info("imgUrl is : {}", imgUrl);

        String height = body.getAsJsonObject().get("mntninfohght").toString();
//    log.info("height is : {}m", height);

        mountain = MountainInfo.builder()
                .name(infoTemp.getName())
                .description(description)
                .address(infoTemp.getAddress())
                .alt(Double.parseDouble(height))
                .imgUrl(imgUrl)
                .build();

      } else {
        log.error("mountain name that fail to get data is : {}", infoTemp.getName());
        mountain = MountainInfo.builder()
                .name(infoTemp.getName())
                .description("")
                .address(infoTemp.getAddress())
                .alt(0d)
                .imgUrl("")
                .build();
      }
      mountainInfoRepository.save(mountain);
    }
  }

  @Override
  public void getMountainPeak() {

  }

  @Override
  public void getMountainDesk() {

  }

  @Override
  public void getMountainEntry() {

  }

  // data 가져오기 api 호출
  private ResponseEntity<String> callApi(String url) {
    RestTemplate restTemplate = new RestTemplate();
    // request api and get response
    return restTemplate.getForEntity(url, String.class);
  }

}
