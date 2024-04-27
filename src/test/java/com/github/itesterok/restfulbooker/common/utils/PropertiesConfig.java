package com.github.itesterok.restfulbooker.common.utils;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:app.properties"})
public interface PropertiesConfig extends Config {
    String hostname();

    @Key("auth.username")
    String username();

    @Key("auth.password")
    String password();
}
