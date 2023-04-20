package com.data.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "mountain_entry")
public class MountainEntry {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "mountain_entry_id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
  Long id;

  @ManyToOne
  @JoinColumn(name = "mountain_id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
  MountainInfo mountainInfo;

  @Column(name = "mountain_entry_name", nullable = false, length = 50)
  String name;

  @Column(name = "mountain_entry_description", nullable = false, length = 200)
  String description;

  @Column(name = "mountain_entry_address", nullable = false, length = 200)
  String address;

  @Column(name = "mountain_entry_lat", nullable = false)
  Double lat;
  @Column(name = "mountain_entry_lng", nullable = false)
  Double lng;
  @Column(name = "mountain_entry_alt", nullable = false)
  Double alt;
}
