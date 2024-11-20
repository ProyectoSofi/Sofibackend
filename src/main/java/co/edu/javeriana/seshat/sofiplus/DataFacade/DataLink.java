package co.edu.javeriana.seshat.sofiplus.DataFacade;

import co.edu.javeriana.seshat.sofiplus.DataFacade.Entities.*;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.FamiempresaEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.FamiempresaEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.UsuarioEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.UsuarioEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities.ItemEntityRepository;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.FrontEntities.Cliente;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public class DataLink implements DataBroker {
    @Resource
    private EventoEntityRepository eventoEntityRepository;

    @Resource
    private ConsolidadoEntityRepository consolidadoEntityRepository;

    @Resource
    private ReaEntityRepository reaEntityRepository;
    @Resource
    private PersonaEntityRepository personaEntityRepository;

    @Resource
    private ItemEntityRepository itemEntityRepository;

    @Resource
    private FamiempresaEntityRepository famiempresaEntityRepository;

    @Resource
    private RecursoEntityRepository recursoEntityRepository;

    @Resource
    private UsuarioEntityRepository usuarioEntityRepository;

    @PersistenceContext
    private EntityManager em;


    @Override
    public Optional<EventoEntity> requerirEvento(EventoEntityPK pk) {
        return eventoEntityRepository.findById(pk);
    }

    @Override
    public List<EventoEntity> requerirEventos(String nitFamiempresa, String tipo) {
        return eventoEntityRepository.findAllByNitFamiempresaAndTipoEvento(nitFamiempresa, tipo);
    }

    @Override
    public List<EventoEntity> requerirEventosPorFecha(String nitFamiempresa, String tipo, Date fecha) {
        return eventoEntityRepository.findAllByNitFamiempresaAndTipoEventoAndFecha(nitFamiempresa, tipo, fecha);
    }

    @Override
    public List<EventoEntity> requerirEventosPorAgente(String nitFamiempresa, String tipo, boolean externo, String agenteId) {
        if (externo) {
            return eventoEntityRepository.findAllByNitFamiempresaAndTipoEventoAndAgenteExterno(nitFamiempresa, tipo, agenteId);
        } else {
            return eventoEntityRepository.findAllByNitFamiempresaAndTipoEventoAndAgenteInterno(nitFamiempresa, tipo, agenteId);
        }
    }

    @Override
    public ReaConsolidated requerirRea(EventoEntityPK evento) {
        Optional<EventoEntity> eventoProspect = eventoEntityRepository.findById(evento);
        if (eventoProspect.isEmpty()) {
            return null;
        }
        TypedQuery<ReaEntity> query = em.createQuery("select rea from ReaEntity rea where rea.idEvento =: id", ReaEntity.class)
                .setParameter("id", evento.getIdEvento());
        List<ReaEntity> reas = query.getResultList();
        return new ReaConsolidated(eventoProspect.get(), reas);
    }

    @Override
    public void registrarRea(EventoEntity evento, List<ReaEntity> detalles) {
        try {
            eventoEntityRepository.save(evento);
            reaEntityRepository.saveAll(detalles);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<String, Object> requerirMetadata(String key) {
        return null;
    }

    @Override
    public void registrarMetadata(String key, HashMap<String, Object> document) {

    }

    @Override
    public void registrarAgente(PersonaEntity persona) {
        this.personaEntityRepository.save(persona);
    }

    @Override
    public PersonaEntity requerirAgente(String id) {
        Optional<PersonaEntity> persona = personaEntityRepository.findById(id);
        if (persona.isEmpty()) {
            return null;
        }
        return persona.get();
    }

    @Override
    public String registrarFamiempresa(FamiempresaEntity famiempresa) {
        famiempresaEntityRepository.save(famiempresa);
        return famiempresa.getNit();
    }

    @Override
    public String registrarRecurso(RecursoEntity recurso) {
        recursoEntityRepository.save(recurso);
        return recurso.getIdRecurso();
    }


    @Override
    public Optional<UsuarioEntity> requerirUsuarioPorEmail(String email) {
        return usuarioEntityRepository.findUsuarioEntityByEmail(email);
    }

    @Override
    public Optional<UsuarioEntity> requerirUsuario(String id) {
        return usuarioEntityRepository.findById(id);
    }

    @Override
    public UsuarioEntity registrarUsuario(UsuarioEntity usuario) {
        return usuarioEntityRepository.save(usuario);
    }

    @Override
    public Cliente buscarCliente(String id) {
        return null;
    }

    @Override
    public void registrarConsolidados(List<ConsolidadoEntity> consolidados) {
        consolidadoEntityRepository.saveAll(consolidados);
    }

    @Override
    public Optional<ConsolidadoEntity> requerirConsolidado(ConsolidadoEntityPK pk) {
        return consolidadoEntityRepository.findById(pk);
    }

    @Override
    public List<ConsolidadoEntity> requerirConsolidadosPorFamiempresa(String nitFamiempresa) {
        return consolidadoEntityRepository.findAllByNitFamiempresa(nitFamiempresa);
    }
}
