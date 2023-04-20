package com.data.data.controller;

import com.data.data.service.MountainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/mountain")
@RequiredArgsConstructor
public class MountainController {

  private final MountainService mountainService;

  @PostMapping("/info")
  public ResponseEntity<?> getMountainInfo() {

    mountainService.getMountainInfo();
    return new ResponseEntity<>("100대 명산 리스트를 가져오는데 성공했습니다.", HttpStatus.CREATED);
  }

  @PostMapping("/peak")
  public ResponseEntity<?> getMountainPeak() {

    mountainService.getMountainPeak();
    return new ResponseEntity<>("100대 명산 봉우리 정보를 가져오는데 성공했습니다.", HttpStatus.CREATED);
  }

  @PostMapping("/desk")
  public ResponseEntity<?> getMountainDesk() {

    mountainService.getMountainDesk();
    return new ResponseEntity<>("100대 명산 안내소 정보를 가져오는데 성공했습니다.", HttpStatus.CREATED);
  }

  @PostMapping("/entry")
  public ResponseEntity<?> getMountainEntry() {

    mountainService.getMountainEntry();
    return new ResponseEntity<>("100대 명산 입구 정보를 가져오는데 성공했습니다.", HttpStatus.CREATED);
  }

}
