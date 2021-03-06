package com.example.brendon.registrodealunosv1.Models;


import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

// THIS CODE IS GENERATED BY ObjectBox, DO NOT EDIT.

/**
 * ObjectBox generated Cursor implementation for "Aluno".
 * Note that this is a low-level class: usually you should stick to the Box class.
 */
public final class AlunoCursor extends Cursor<Aluno> {
    @Internal
    static final class Factory implements CursorFactory<Aluno> {
        @Override
        public Cursor<Aluno> createCursor(io.objectbox.Transaction tx, long cursorHandle, BoxStore boxStoreForEntities) {
            return new AlunoCursor(tx, cursorHandle, boxStoreForEntities);
        }
    }

    private static final Aluno_.AlunoIdGetter ID_GETTER = Aluno_.__ID_GETTER;


    private final static int __ID_nome = Aluno_.nome.id;
    private final static int __ID_curso = Aluno_.curso.id;
    private final static int __ID_faculdade = Aluno_.faculdade.id;

    public AlunoCursor(io.objectbox.Transaction tx, long cursor, BoxStore boxStore) {
        super(tx, cursor, Aluno_.__INSTANCE, boxStore);
    }

    @Override
    public final long getId(Aluno entity) {
        return ID_GETTER.getId(entity);
    }

    /**
     * Puts an object into its box.
     *
     * @return The ID of the object within its box.
     */
    @Override
    public final long put(Aluno entity) {
        String nome = entity.getNome();
        int __id1 = nome != null ? __ID_nome : 0;
        String curso = entity.getCurso();
        int __id2 = curso != null ? __ID_curso : 0;
        String faculdade = entity.getFaculdade();
        int __id3 = faculdade != null ? __ID_faculdade : 0;

        long __assignedId = collect313311(cursor, entity.id, PUT_FLAG_FIRST | PUT_FLAG_COMPLETE,
                __id1, nome, __id2, curso,
                __id3, faculdade, 0, null,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0);

        entity.id = __assignedId;

        return __assignedId;
    }

}
