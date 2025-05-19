package be.ucll.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "my_ponies")
public class Pony {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "the pony name must not be empty")
    @Size(min = 4,max = 20,message = "the name must be between 4 and 20 characters")
    private String name;

    @NotNull(message = "age cannot be null")
    @Min(value = 0,message = "age cannot be less than 0")
    @Max(value = 100,message = "Age cannot be more than 100")
    private int age;

    @Min(value = 40, message = "a pony cannot be under 40cm")
    @Max(value = 147,message = " a pony cannot be over 147 cm")
    @NotNull(message = "size cannot be null")
    private int size;

    public Pony(String name, int age,int size){
        this.name = name;
        this.age = age;
        this.size = size;
    }
    public  Pony(){}



    public void updateNameAndAge(String name, int age,int size) {
        this.name = name;
        this.age = age;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSize() {
        return size;
    }

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}




