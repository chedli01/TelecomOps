package com.coding.internship.auth.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "blacklisted_tokens")
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlackListedToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(unique = true)
    private String token;
}
