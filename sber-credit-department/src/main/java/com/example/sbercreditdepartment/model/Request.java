package com.example.sbercreditdepartment.model;

import com.example.sbercreditdepartment.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "requests")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "user_id",
        foreignKey = @ForeignKey(name = "FK_REQUESTS_USER"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "manager_id", foreignKey = @ForeignKey(name = "FK_REQUESTS_MANAGER"))
    private Manager manager;

    @Column(name = "date_of_request")
    private LocalDateTime dateOfRequest;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "monthly_payment")
    private Double monthlyPayment;

    @Column(name = "maximum_debt")
    private Integer maximumDebt;

    @Column(name = "request_status")
    @Enumerated(value = EnumType.STRING)
    private RequestStatus requestStatus;

    @ManyToOne
    @JoinColumn(name = "credit_type", nullable = false,
    foreignKey = @ForeignKey(name = "FK_REQUESTS_CREDIT_TYPE"))
    private Credit credit;

}
