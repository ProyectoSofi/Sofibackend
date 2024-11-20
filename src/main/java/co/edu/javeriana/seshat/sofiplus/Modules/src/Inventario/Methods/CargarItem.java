package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.ConsolidadoBuilder;
import co.edu.javeriana.seshat.sofiplus.DataFacade.ConsolidadoBuilderFactory;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.Item;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.FrontEntities.ItemCarga;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

@ModuleMethod
public class CargarItem implements ModuleRunnable {

    @Autowired
    ItemEntityRepository repository;

    @Autowired
    ConsolidadoBuilderFactory consolidadoBuilderFactory;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        if (message.getCredentials().isEmpty()) {
            throw new AuthorizationRequiredException();
        }
        ItemCarga itemCarga = (ItemCarga) message.getParams();
        ItemEntity entity = repository.findItemEntityByNitFamiempresaAndCodigo(message.getCredentials().get().getFamiempresaID(), itemCarga.getCodigo());
        entity.setCantidad(entity.getCantidad() + itemCarga.getCantidad());
        double nuevoActivo = itemCarga.getCantidad() * itemCarga.getCosto();
        entity.setCosteTotal(entity.getCosteTotal() + nuevoActivo);
        double costoPromedio = entity.getCosteTotal() / entity.getCantidad();
        entity.setCosteUnitario(costoPromedio);
        ConsolidadoBuilder builder = consolidadoBuilderFactory.getBuilder(message.getCredentials().get().getFamiempresaID());
        builder.sumar("14", nuevoActivo);
        builder.ejecutar();
        return repository.save(entity).getFrontEntity();
    }
}
