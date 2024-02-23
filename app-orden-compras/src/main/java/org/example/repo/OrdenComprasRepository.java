package org.example.repo;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.example.db.OrdenCompras;

@ApplicationScoped
@Transactional
public class OrdenComprasRepository implements PanacheRepositoryBase<OrdenCompras,Integer> {
}
