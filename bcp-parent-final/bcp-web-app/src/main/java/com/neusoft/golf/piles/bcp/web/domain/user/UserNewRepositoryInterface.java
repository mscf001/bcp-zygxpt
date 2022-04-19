package com.neusoft.golf.piles.bcp.web.domain.user;

import com.neusoft.golf.piles.bcp.web.domain.role.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Administrator
 */
public interface UserNewRepositoryInterface extends JpaRepository<UserNewDo,String>, JpaSpecificationExecutor<UserNewDo> {

    Optional<UserNewDo> findByAccount(String account);

    Optional<Set<UserNewDo>> findByRolesContains(RoleDO role);

    List<UserNewDo> findByAccountLike(String account);
}
