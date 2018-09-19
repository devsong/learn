package com.gzs.learn.net.netty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.gzs.learn.net.EchoServer;
import com.gzs.learn.net.NetConstants;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyTimeServer extends ChannelInboundHandlerAdapter implements EchoServer {
    private String address;
    private int port;

    public NettyTimeServer() {}

    public NettyTimeServer(int port) {
        this(NetConstants.BIND_ADDR, port);
    }

    public NettyTimeServer(String address, int port) {
        this.address = address;
        this.port = port;
    }

    @Override
    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyTimeServer());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(address, port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("start error", e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + "\n";
        ByteBuf buf = ctx.alloc().buffer().writeBytes(now.getBytes(), 0, now.getBytes().length);
        ChannelFuture future = ctx.writeAndFlush(buf);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                ctx.close();
            }
        });
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("error ", cause);
    }
}
