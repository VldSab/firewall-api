package ru.libertyfirewall.backendapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.libertyfirewall.backendapi.enumeration.rules.SensitiveCategory;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DLPRule implements Rule{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private Long srcGroupID;
    private Long dstGroupID;
    private SensitiveCategory sensitiveCategory;

}
