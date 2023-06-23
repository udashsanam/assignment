package com.project.coffeshop.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Entity
@Table(name = "user_refresh_token")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRefreshTokenEntity extends BaseEntity{

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "expiry_time")
    private Timestamp expiryTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "token_id")
    private UserTokenEntity userToken;
}
