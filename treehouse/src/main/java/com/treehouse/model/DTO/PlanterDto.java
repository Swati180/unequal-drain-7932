package com.treehouse.model.DTO;

import com.treehouse.model.Planter;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlanterDto{

        @Id
        private Integer PlanterId;
        private Integer PlanterHeight;
        private Integer PlanterCapacity;
        private Integer DrainHoles;
        private String PlanterColor;
        private String  PlanterShape;
        private Integer PlanterStock;
        private Integer PlanterCost;
        private Integer PlanterQuantity;
        private String planterImage;

}
