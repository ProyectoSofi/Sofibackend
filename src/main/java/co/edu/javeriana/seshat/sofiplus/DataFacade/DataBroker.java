package co.edu.javeriana.seshat.sofiplus.DataFacade;

import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.*;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.FamiempresaEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.UsuarioEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.FrontEntities.Cliente;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface DataBroker {

    public Optional<EventoEntity> requerirEvento(EventoEntityPK pk);

    public List<EventoEntity> requerirEventos(String nitFamiempresa, String tipo);

    public List<EventoEntity> requerirEventosPorFecha(String nitFamiempresa, String tipo, Date fecha);

    public List<EventoEntity> requerirEventosPorAgente(String nitFamiempresa, String tipo, boolean externo, String agenteId);

    public ReaConsolidated requerirRea(EventoEntityPK evento);

    public void registrarRea(EventoEntity evento, List<ReaEntity> detalles);

    public void registrarConsolidados(List<ConsolidadoEntity> consolidados);

    public Optional<ConsolidadoEntity> requerirConsolidado(ConsolidadoEntityPK pk);

    public List<ConsolidadoEntity> requerirConsolidadosPorFamiempresa(String nitFamiempresa);

    public HashMap<String, Object> requerirMetadata(String key);

    public void registrarMetadata(String key, HashMap<String, Object> document);

    public void registrarAgente(PersonaEntity persona);

    public PersonaEntity requerirAgente(String id);

    public String registrarFamiempresa(FamiempresaEntity famiempresa);

    public String registrarRecurso(RecursoEntity recurso);

    public Optional<UsuarioEntity> requerirUsuario(String id);

    public Optional<UsuarioEntity> requerirUsuarioPorEmail(String email);

    public UsuarioEntity registrarUsuario(UsuarioEntity usuario);

    public Cliente buscarCliente(String id);
}
