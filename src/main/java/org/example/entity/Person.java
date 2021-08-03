package org.example.entity;

import javax.persistence.*;

@Entity
@PersistenceUnit(unitName="Person")
public class Person extends CoreEntity{
}
