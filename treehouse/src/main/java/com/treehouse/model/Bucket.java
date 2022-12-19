package com.treehouse.model;

import com.treehouse.model.DTO.PlantDto;
import com.treehouse.model.DTO.PlanterDto;
import com.treehouse.model.DTO.SeedsDto;
import lombok.*;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bucketId;
    private Integer totalPrice;
    private Integer plantPrice;
    private Integer planterPrice;
    private Integer seedsPrice;
    private Integer plantQuantity;
    private Integer planterQuantity;
    private Integer seedsQuantity;
    private Integer totalItems;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<PlanterDto> planters = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<PlantDto> plants = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<SeedsDto> seeds = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Orders orders;

}
