package co.edu.uco.talklate.util.mapper;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class GenericMapper {

    /**
     * Mapea un objeto de origen a un objeto de destino del tipo dado.
     *
     * @param <S> Tipo del objeto fuente
     * @param <D> Tipo del objeto destino
     * @param source Objeto fuente desde el cual se van a copiar las propiedades
     * @param destinationType Clase del objeto destino
     * @return Objeto mapeado al tipo de destino
     */
    public <S, D> D map(S source, Class<D> destinationType) {
        if (source == null) {
            return null;
        }
        try {
            D destination = destinationType.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, destination);
            return destination;
        } catch (Exception e) {
            throw new RuntimeException("Error al mapear el objeto", e);
        }
    }
}
