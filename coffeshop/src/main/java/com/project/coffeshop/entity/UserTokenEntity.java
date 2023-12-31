package com.project.coffeshop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "user_token")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenEntity implements Serializable {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "user_token", nullable = false)
    private String accessToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "expiry_time")
    private Timestamp expiryTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
