package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.RoleDaoImpl;
import by.fpmibsu.Dao.RoleDao;
import by.fpmibsu.Entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoleService {
    private RoleDao roleDao;

    public RoleService() {
        this.roleDao = new RoleDaoImpl();
    }

    static final Logger roleServiceLogger = LogManager.getLogger(RoleService.class);

    public Role findEntityById(Long id) {
        roleServiceLogger.debug("Find role by id");
        return new RoleDaoImpl().findEntityById(id);
    }
}
