package com.example.server.auth.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {
  private String username;
  private String password;
  private String email;
  private Boolean enabled;
}
