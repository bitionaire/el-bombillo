package org.bitionaire.elbombillo.account.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.dropwizard.jackson.Jackson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static io.dropwizard.testing.FixtureHelpers.*;

public class AccountTest {

    private ObjectMapper mapper;

    @Before
    public void setUpObjectMapper() {
        mapper = Jackson.newObjectMapper();
    }

    @Test
    public void testAbbreviatedSerialization() throws Exception {
        final ObjectWriter objectWriter = mapper.writerWithView(AccountRepresentation.Abbreviated.class).forType(Account.class);

        final Account account = new Account(new Long(1), "john.doe", "John", "Doe", "john.doe@email.com");
        final String expected = objectWriter.writeValueAsString(mapper.readValue(fixture("fixtures/account-abbreviated.json"), Account.class));
        assertEquals(expected, objectWriter.writeValueAsString(account));
    }

    @Test
    public void testCompleteSerialization() throws Exception {
        final ObjectWriter objectWriter = mapper.writerWithView(AccountRepresentation.Complete.class).forType(Account.class);

        final Account account = new Account(new Long(1), "john.doe", "John", "Doe", "john.doe@email.com");
        final String expected = objectWriter.writeValueAsString(mapper.readValue(fixture("fixtures/account-complete.json"), Account.class));
        assertEquals(expected, objectWriter.writeValueAsString(account));
    }

    @Test
    public void testDeserialization() throws Exception {
        final Account account = new Account(new Long(1), "john.doe", "John", "Doe", "john.doe@email.com");
        assertEquals(mapper.readValue(fixture("fixtures/account-complete.json"), Account.class), account);
    }
}