package be.ucll.unit.repository;

import be.ucll.model.Pony;
import be.ucll.repository.PonyRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

public class PonyRepositoryStub implements PonyRepository {
    private final Map<Long, Pony> store = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Pony findByName(String name) {
        return store.values().stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public <S extends Pony> S save(S entity) {
        if (entity.getName() == null) return null;

        Optional<Long> existingId = store.entrySet().stream()
                .filter(e -> e.getValue().getName().equals(entity.getName()))
                .map(Map.Entry::getKey)
                .findFirst();

        long id = existingId.orElseGet(idGenerator::getAndIncrement);
        store.put(id, entity);
        return entity;
    }

    @Override
    public List<Pony> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Pony entity) {
        store.values().removeIf(p -> p.getName().equals(entity.getName()));
    }

    @Override
    public void deleteAll() {
        store.clear();
    }

    // Unused methods – stubbed as empty/default
    @Override public Optional<Pony> findById(Long aLong) { return Optional.empty(); }
    @Override public boolean existsById(Long aLong) { return false; }
    @Override public List<Pony> findAllById(Iterable<Long> longs) { return List.of(); }
    @Override public long count() { return store.size(); }
    @Override public void deleteById(Long aLong) {}
    @Override public void deleteAllById(Iterable<? extends Long> longs) {}
    @Override public void deleteAll(Iterable<? extends Pony> entities) {}
    @Override public <S extends Pony> List<S> saveAll(Iterable<S> entities) { return List.of(); }
    @Override public List<Pony> findAll(org.springframework.data.domain.Sort sort) { return List.of(); }
    @Override public org.springframework.data.domain.Page<Pony> findAll(org.springframework.data.domain.Pageable pageable) { return null; }
    @Override public void flush() {}
    @Override public <S extends Pony> S saveAndFlush(S entity) { return save(entity); }
    @Override public <S extends Pony> List<S> saveAllAndFlush(Iterable<S> entities) { return List.of(); }
    @Override public void deleteAllInBatch(Iterable<Pony> entities) {}
    @Override public void deleteAllByIdInBatch(Iterable<Long> longs) {}
    @Override public void deleteAllInBatch() {}
    @Override public Pony getOne(Long aLong) { return null; }
    @Override public Pony getById(Long aLong) { return null; }
    @Override public Pony getReferenceById(Long aLong) { return null; }
    @Override public <S extends Pony> Optional<S> findOne(org.springframework.data.domain.Example<S> example) { return Optional.empty(); }
    @Override public <S extends Pony> List<S> findAll(org.springframework.data.domain.Example<S> example) { return List.of(); }
    @Override public <S extends Pony> List<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Sort sort) { return List.of(); }
    @Override public <S extends Pony> org.springframework.data.domain.Page<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Pageable pageable) { return null; }
    @Override public <S extends Pony> long count(org.springframework.data.domain.Example<S> example) { return 0; }
    @Override public <S extends Pony> boolean exists(org.springframework.data.domain.Example<S> example) { return false; }
    @Override public <S extends Pony, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {return null;}
    public void clear() {store.clear();}

}
