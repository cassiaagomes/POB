package daodb4o;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

public abstract class DAO<T> implements DAOInterface<T> {
    protected static ObjectContainer manager;

    public static void open() {
        manager = Util.conectarBanco(); // banco local
        // manager = Util.conectarBancoRemoto(); // banco remoto
    }

    public static void close() {
        Util.desconectar();
    }

    // ---------- CRUD -----------------------
    public void create(T obj) {
        if (manager == null) {
            throw new IllegalStateException("A conexão com o banco de dados não foi estabelecida.");
        }
        manager.store(obj); 
    }

    public T update(T obj) {
        if (manager == null) {
            throw new IllegalStateException("A conexão com o banco de dados não foi estabelecida.");
        }
        manager.store(obj); 
        return obj;
    }

    public void delete(T obj) {
        if (manager == null) {
            throw new IllegalStateException("A conexão com o banco de dados não foi estabelecida.");
        }
        manager.delete(obj);  
    }

    public void refresh(T obj) {
        if (manager == null) {
            throw new IllegalStateException("A conexão com o banco de dados não foi estabelecida.");
        }
        manager.ext().refresh(obj, Integer.MAX_VALUE);  
    }

    @SuppressWarnings("unchecked")
    public List<T> readAll() {
        if (manager == null) {
            throw new IllegalStateException("A conexão com o banco de dados não foi estabelecida.");
        }
        manager.ext().purge(); // Limpar cache do manager

        Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        Query q = manager.query();
        q.constrain(type);
        return (List<T>) q.execute();
    }

    // -------- Transações ---------------
    public static void begin() {

    }

    public static void commit() {
        if (manager != null) {
            manager.commit();
            manager.ext().purge();  
        }
    }

    public static void rollback() {
        if (manager != null) {
            manager.rollback();  
        }
    }

    /**
     * Gerar novo ID para o tipo T, baseando-se no maior valor do atributo "id".
     */
    public <X> int gerarId(Class<X> classe) {
        
        if (manager.query(classe).isEmpty()) {
            return 1; 
        } else {
            
            Query q = manager.query();
            q.constrain(classe);
            q.descend("id").orderDescending();
            List<X> resultados = q.execute();
            if (resultados.isEmpty())
                return 1; 
            else
                try {
                    
                    X objeto = resultados.get(0); 
                    
                    int id = 0;
                    for (Field f : getAllFieldsList(classe)) {
                        if (f.getName().equals("id")) {
                            f.setAccessible(true); 
                            id = (Integer) f.get(objeto);
                        }
                    }
                    if (id == 0)
                        throw new NoSuchFieldException();

                    return id + 1; // Retorna o próximo ID
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException("Classe " + classe + " não tem atributo 'id'");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Classe " + classe + " - atributo 'id' inacessível");
                }
        }
    }

    /**
     * Retorna uma lista com todos os campos de uma classe (incluindo heranças).
     */
    public static <X> List<Field> getAllFieldsList(final Class<X> cls) {
        final List<Field> allFields = new ArrayList<>();
        Class<?> currentClass = cls;
        while (currentClass != null) {
            final Field[] declaredFields = currentClass.getDeclaredFields();
            Collections.addAll(allFields, declaredFields);
            currentClass = currentClass.getSuperclass();
        }
        return allFields;
    }

    /**
     * Consulta genérica para buscar um atributo específico.
     */
    public List<T> readByField(String fieldName, Object value) {
        Query q = manager.query();
        q.constrain(getGenericClass());
        q.descend(fieldName).constrain(value);
        return q.execute();
    }

    /**
     * Retorna a classe genérica da DAO.
     */
    @SuppressWarnings("unchecked")
    private Class<T> getGenericClass() {
        return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
}
