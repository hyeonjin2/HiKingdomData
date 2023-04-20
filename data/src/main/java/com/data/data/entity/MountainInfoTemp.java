package com.data.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "mountain_info_temp")
public class MountainInfoTemp {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
  Long id;

  @Column(name = "mountain_info_name", nullable = false, length = 20)
  String name;

  @Column(name = "mountain_info_alt", nullable = false)
  Double alt;

  @Column(name = "mountain_info_address", nullable = false, length = 200)
  String address;
}
