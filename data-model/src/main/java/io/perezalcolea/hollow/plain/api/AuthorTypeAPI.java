package io.perezalcolea.hollow.plain.api;

import com.netflix.hollow.api.custom.HollowObjectTypeAPI;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;

@SuppressWarnings("all")
public class AuthorTypeAPI extends HollowObjectTypeAPI {

    private final AuthorDelegateLookupImpl delegateLookupImpl;

    public AuthorTypeAPI(BooksAPI api, HollowObjectTypeDataAccess typeDataAccess) {
        super(api, typeDataAccess, new String[] {
            "id",
            "authorName"
        });
        this.delegateLookupImpl = new AuthorDelegateLookupImpl(this);
    }

    public long getId(int ordinal) {
        if(fieldIndex[0] == -1)
            return missingDataHandler().handleLong("Author", ordinal, "id");
        return getTypeDataAccess().readLong(ordinal, fieldIndex[0]);
    }

    public Long getIdBoxed(int ordinal) {
        long l;
        if(fieldIndex[0] == -1) {
            l = missingDataHandler().handleLong("Author", ordinal, "id");
        } else {
            boxedFieldAccessSampler.recordFieldAccess(fieldIndex[0]);
            l = getTypeDataAccess().readLong(ordinal, fieldIndex[0]);
        }
        if(l == Long.MIN_VALUE)
            return null;
        return Long.valueOf(l);
    }



    public int getAuthorNameOrdinal(int ordinal) {
        if(fieldIndex[1] == -1)
            return missingDataHandler().handleReferencedOrdinal("Author", ordinal, "authorName");
        return getTypeDataAccess().readOrdinal(ordinal, fieldIndex[1]);
    }

    public StringTypeAPI getAuthorNameTypeAPI() {
        return getAPI().getStringTypeAPI();
    }

    public AuthorDelegateLookupImpl getDelegateLookupImpl() {
        return delegateLookupImpl;
    }

    @Override
    public BooksAPI getAPI() {
        return (BooksAPI) api;
    }

}