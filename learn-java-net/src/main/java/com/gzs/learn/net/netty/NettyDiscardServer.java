package com.gzs.learn.net.netty;

import com.gzs.learn.net.DiscardServer;
import com.gzs.learn.net.NetConstants;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyDiscardServer extends ChannelInboundHandlerAdapter implements DiscardServer {
    private String address;
    private int port;

    public NettyDiscardServer() {
        this(NetConstants.BIND_ADDR, NetConstants.PORT_DEFAULT);
    }

    public NettyDiscardServer(int port) {
        this(NetConstants.BIND_ADDR, port);
    }


    public NettyDiscardServer(String address, int port) {
        this.address = address;
        this.port = port;
    }

    @Override
    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) // (3)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyDiscardServer());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128) // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
            ChannelFuture f = b.bind(address, port).sync(); // (7)
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("start error", e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        log.info("recv msg:{}", buf.toString(NetConstants.UTF8));
        ReferenceCountUtil.release(msg);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("error ", cause);
    }
}
