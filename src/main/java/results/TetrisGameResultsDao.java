package results;

import util.jpa.GenericJpaDao;

import javax.persistence.Persistence;
import java.util.List;

/**
 * DAO class for the {@link TetrisGameResults} entity.
 */
public class TetrisGameResultsDao extends GenericJpaDao<TetrisGameResults> {

    private static TetrisGameResultsDao instance;

    private TetrisGameResultsDao() {
        super(TetrisGameResults.class);
    }

    public static TetrisGameResultsDao getInstance() {
        if (instance == null) {
            instance = new TetrisGameResultsDao();
            instance.setEntityManager(Persistence.createEntityManagerFactory("jpa-persistence-unit-1").createEntityManager());
        }
        return instance;
    }

    /**
     * Returns the list of {@code n} best results with respect to the time
     * spent for solving the puzzle.
     *
     * @param n the maximum number of results to be returned
     * @return the list of {@code n} best results with respect to the time
     * spent for solving the puzzle
     */
    public List<TetrisGameResults> findBest(int n) {
        return entityManager.createQuery("SELECT r FROM TetrisGameResults r ORDER BY r.score DESC, r.level DESC, r.created DESC", TetrisGameResults.class)
                .setMaxResults(n)
                .getResultList();
    }

}
