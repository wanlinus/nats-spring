package cn.wanlinus.nats;

import io.nats.client.Connection;
import io.nats.client.ConnectionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Nats 连接监听
 *
 * @author wanli
 * @date 2019-01-09 16:19
 */
public class NatsListener implements ConnectionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionListener.class);

    @Override
    public void connectionEvent(Connection conn, Events type) {
        LOGGER.info(String.format("connection status: %s", conn.getStatus()));
        if (conn.getStatus().equals(Connection.Status.CLOSED)) {
            LOGGER.info("NATS connection is closed, server is shutting down");
            System.exit(1);
        }
    }
}
