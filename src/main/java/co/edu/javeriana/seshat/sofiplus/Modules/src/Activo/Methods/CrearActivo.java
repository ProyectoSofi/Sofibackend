package co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Methods;

import co.edu.javeriana.seshat.sofiplus.DataFacade.ReaBuilder;
import co.edu.javeriana.seshat.sofiplus.DataFacade.ReaBuilderFactory;
import co.edu.javeriana.seshat.sofiplus.Kernel.AuthorizationRequiredException;
import co.edu.javeriana.seshat.sofiplus.Kernel.DateFromFront;
import co.edu.javeriana.seshat.sofiplus.Kernel.ModuleRunnable;
import co.edu.javeriana.seshat.sofiplus.Kernel.RequestMessage;
import co.edu.javeriana.seshat.sofiplus.Modules.ModuleMethod;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Entities.ActivoEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.Entities.ActivoEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Activo.FrontEntities.Activo;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@ModuleMethod
public class CrearActivo implements ModuleRunnable {

    @Autowired
    private ReaBuilderFactory factory;

    @Autowired
    private ActivoEntityRepository repository;

    @Override
    public Object run(RequestMessage message) throws AuthorizationRequiredException {
        if (message.getCredentials().isEmpty()) {
            throw new AuthorizationRequiredException();
        }
        Activo activo = (Activo) message.getParams();
        try {
            int periodos = 0;
            if (Objects.equals(activo.getUnidadTiempo(), "Dias")) {
                periodos = (int) Math.ceil(activo.getVidaUtil() / 30.0);
            }
            if (Objects.equals(activo.getUnidadTiempo(), "Meses")) {
                periodos = (int) activo.getVidaUtil();
            }
            if (Objects.equals(activo.getUnidadTiempo(), "Años")) {
                periodos = (int) (activo.getVidaUtil() * 12.0);
            }
            Date fechaBase = DateFromFront.formatUtilDate(activo.getFechaIngreso());
            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaBase);
            for (int i = 0; i < periodos; i++) {
                ReaBuilder builder = factory.getOnlyReaBuilder(message.getCredentials().get().getFamiempresaID());
                builder.registrarEvento(UUID.randomUUID().toString(), new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()), message.getCredentials().get().getId(), message.getCredentials().get().getId(), "DEPRECIACION");
                builder.sumar("5160", (activo.getCosto() * activo.getCantidad()) / periodos);
                builder.ejecutar();
                cal.add(Calendar.MONTH, 1);
            }
            ActivoEntity activoEntity = new ActivoEntity(activo, message.getCredentials().get().getFamiempresaID());
            return repository.save(activoEntity).getFrontEntity();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
