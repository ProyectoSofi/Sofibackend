package co.edu.javeriana.seshat.sofiplus.Modules.src.ReciboDeCaja.Entities;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReciboDeCajaMetadataRepository extends MongoRepository<ReciboDeCajaMetadata, String> {
    ReciboDeCajaMetadata findByDescripcion(String descripcion);
}
