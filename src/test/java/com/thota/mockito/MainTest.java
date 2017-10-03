package com.thota.mockito;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.thota.mockito.domain.User;
import com.thota.mockito.service.UserService;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MainTest {
    private static final Log LOG = LogFactory.getLog(MainTest.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        final JdbcOperations jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("insert into user (id, first_name, last_name) values (1, 'Joe', 'Smith');");
        jdbcTemplate.update("insert into user (id, first_name, last_name) values (2, 'John', 'Doe');");
        jdbcTemplate.update("insert into user (id, first_name, last_name) values (3, 'Mary', 'Sue');");
    }

    @Test
    public void testOne() throws Exception {
        LOG.debug("TEST ONE BEGIN");
        final String lastName = "Smith";
        final User user = userService.getUserByLastName(lastName);
        LOG.debug(user);
        assertNotNull(user);
        assertEquals(lastName, user.getLastName());
        LOG.debug("TEST ONE END");
    }

    @Test
    public void testTwo() throws Exception {
        LOG.debug("TEST TWO BEGIN");
        final Iterable<User> users = userService.getAllUsersOrderByLastName();
        final List<String> actualLastNames = new ArrayList<>();
        users.forEach(user -> actualLastNames.add(user.getLastName()));
        final List<String> expectedLastNames = Arrays.asList("Doe", "Smith", "Sue");
        assertEquals(expectedLastNames, actualLastNames);
        LOG.debug("TEST TWO END");
    }
    @After
    public void tearDown() {
        final JdbcOperations jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("delete from user;");
    }

}