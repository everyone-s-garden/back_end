package com.everyonegarden.garden;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "garden")
public class Garden {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long gardenId;

    private double latitude;
    private double longitude;
    private String address;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GardenType type;

    private String link;
    private String price;

    private String contact;
    private String size;

    private LocalDateTime recruitStartDate;
    private LocalDateTime recruitEndDate;
    private LocalDateTime useStartDate;
    private LocalDateTime useEndDate;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public Garden editGarden(Garden editedGarden) {
        address = editedGarden.getAddress();
        name = editedGarden.getName();
        link = editedGarden.getLink();
        price = editedGarden.getPrice();
        size = editedGarden.getSize();
        contact = editedGarden.getContact();

        return this;
    }

    public Garden editGardenIgnoreNull(Garden editedGarden) {
        if (editedGarden.getAddress() != null) address = editedGarden.getAddress();
        if (editedGarden.getName() != null) name = editedGarden.getName();
        if (editedGarden.getLink() != null) link = editedGarden.getLink();
        if (editedGarden.getPrice() != null) price = editedGarden.getPrice();
        if (editedGarden.getSize() != null) size = editedGarden.getSize();
        if (editedGarden.getContact() != null) contact = editedGarden.getContact();

        return this;
    }

}