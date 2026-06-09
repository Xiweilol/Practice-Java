package gm.zona_fit.modulo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
//genrar automaticamente getter y setter
@Data
//constructor vacio
@NoArgsConstructor
@AllArgsConstructor
//
@ToString
@EqualsAndHashCode
public class Cliente {
    //llave primaria
    @Id
    //generar llave automaticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String membresia;

}


