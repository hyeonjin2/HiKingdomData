package com.data.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "mountain_peak")
public class MountainPeak {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "mountain_peak_id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
  Long id;

  @ManyToOne
  @JoinColumn(name = "mountain_id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
  MountainInfo mountainInfo;

  @Column(name = "mountain_peak_name", nullable = false, length = 50)
  String name;

  @Column(name = "mountain_peak_address", nullable = false, length = 200)
  String address;

  @Column(name = "mountain_peak_lat", nullable = false)
  Double lat;
  @Column(name = "mountain_peak_lng", nullable = false)
  Double lng;
  @Column(name = "mountain_peak_alt", nullable = false)
  Double alt;
}
