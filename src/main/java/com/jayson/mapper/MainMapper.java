package com.jayson.mapper;

import com.jayson.entity.Event;
import com.jayson.entity.EventType;
import com.jayson.entity.Room;
import com.jayson.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * Created by michaelod on 2018/7/17.
 */
@Mapper
public interface MainMapper {

    @Select("select * from Events ")
    List<Event>  getAllEvents();

    @Select("select * from Rooms ")
    List<Room> getAllRooms();

    @Select("select * from Rooms where id = #{id}")
    Room getRoomById(@Param("id") int id);

    @Select("select * from Users where id = #{id}")
    User getUserById(@Param("id") int id);

    @Select("select * from RventTypes where id = #{id}")
    EventType getEventTypeById(@Param("id") int id);


}

