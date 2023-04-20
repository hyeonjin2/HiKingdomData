package com.data.data.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MountainServiceImpl implements MountainService {
  @Value("servicekey")
  String key;
  String url;

  @Override
  public void getMountainInfo() {
    url = "https://apis.data.go.kr/B553662/sceneryInfoService/getSceneryInfoList?serviceKey=" + key + "&searchWrd=" + "&numOfRows=10000&pageNo=1&type=xml&srchPlaceTpeCd=SCENERY";
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
}
