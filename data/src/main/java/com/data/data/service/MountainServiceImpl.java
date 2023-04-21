package com.data.data.service;

import com.data.data.entity.MountainInfoTemp;
import com.data.data.repository.MountainInfoTempRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class MountainServiceImpl implements MountainService {
  private final MountainInfoTempRepository mountainInfoTempRepository;
  @Value("${value.serviceKey}")
  String key;

  List<MountainInfoTemp> tempList;

  @Override
  public void getMountainInfo() {
    tempList = mountainInfoTempRepository.findAll();
//    for (MountainInfoTemp infoTemp : tempList) {
//      StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B553662/sceneryInfoService/getSceneryInfoList?serviceKey=");
//      urlBuilder.append(key).append("&searchWrd=").append(infoTemp.getName()).append("&numOfRows=1&pageNo=1&type=xml&srchPlaceTpeCd=SCENERY");

    ResponseEntity<String> response = callApi("http://api.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice?ServiceKey=" + key + "&mntnNm=" + "관악산" + "&numOfRows=1&pageNo=1");

    JsonObject jsonObject = JsonParser.parseString(Objects.requireNonNull(response.getBody())).getAsJsonObject();
    log.info("jsonObject is : {}", jsonObject);

    JsonElement body = jsonObject.get("response").getAsJsonObject().get("body").getAsJsonObject().get("items").getAsJsonObject().get("item");
    log.info("body is : {}", body);

    String description = body.getAsJsonObject().get("mntninfodtlinfocont").toString().replace("<BR>", "\n");
    log.info("description is : {}", description);

    String imgUrl = body.getAsJsonObject().get("mntnattchimageseq").toString();
    log.info("imgUrl is : {}", imgUrl);

    String height = body.getAsJsonObject().get("mntninfohght").toString();
    log.info("height is : {}m", height);

//    }
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
