package cz.senslog.provider.security;

import cz.senslog.model.util.PrivilegeBitSet;

public class PrivilegeDefinition {

    public static final PrivilegeBitSet OBSERVATION_CREATE =
            new PrivilegeBitSet("1000000000000000000000000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet OBSERVATION_READ =
            new PrivilegeBitSet("0100000000000000000000000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet OBSERVATION_UPDATE =
            new PrivilegeBitSet("0010000000000000000000000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet OBSERVATION_DELETE =
            new PrivilegeBitSet("0001000000000000000000000000000000000000000000000000000000000000");

    public static final PrivilegeBitSet PHENOMENON_CREATE =
            new PrivilegeBitSet("0000100000000000000000000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet PHENOMENON_READ =
            new PrivilegeBitSet("0000010000000000000000000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet PHENOMENON_UPDATE =
            new PrivilegeBitSet("0000001000000000000000000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet PHENOMENON_DELETE =
            new PrivilegeBitSet("0000000100000000000000000000000000000000000000000000000000000000");

    public static final PrivilegeBitSet PRIVILEGE_CREATE =
            new PrivilegeBitSet("0000000010000000000000000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet PRIVILEGE_READ =
            new PrivilegeBitSet("0000000001000000000000000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet PRIVILEGE_UPDATE =
            new PrivilegeBitSet("0000000000100000000000000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet PRIVILEGE_DELETE =
            new PrivilegeBitSet("0000000000010000000000000000000000000000000000000000000000000000");

    public static final PrivilegeBitSet SENSOR_CREATE =
            new PrivilegeBitSet("0000000000001000000000000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet SENSOR_READ =
            new PrivilegeBitSet("0000000000000100000000000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet SENSOR_UPDATE =
            new PrivilegeBitSet("0000000000000010000000000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet SENSOR_DELETE =
            new PrivilegeBitSet("0000000000000001000000000000000000000000000000000000000000000000");

    public static final PrivilegeBitSet UNIT_GROUP_CREATE =
            new PrivilegeBitSet("0000000000000000100000000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet UNIT_GROUP_READ =
            new PrivilegeBitSet("0000000000000000010000000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet UNIT_GROUP_UPDATE =
            new PrivilegeBitSet("0000000000000000001000000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet UNIT_GROUP_DELETE =
            new PrivilegeBitSet("0000000000000000000100000000000000000000000000000000000000000000");

    public static final PrivilegeBitSet UNITS_CREATE =
            new PrivilegeBitSet("0000000000000000000010000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet UNITS_READ =
            new PrivilegeBitSet("0000000000000000000001000000000000000000000000000000000000000000");
    public static final PrivilegeBitSet UNITS_UPDATE =
            new PrivilegeBitSet("0000000000000000000000100000000000000000000000000000000000000000");
    public static final PrivilegeBitSet UNITS_DELETE =
            new PrivilegeBitSet("0000000000000000000000010000000000000000000000000000000000000000");

    public static final PrivilegeBitSet USER_CREATE =
            new PrivilegeBitSet("0000000000000000000000001000000000000000000000000000000000000000");
    public static final PrivilegeBitSet USER_READ =
            new PrivilegeBitSet("0000000000000000000000000100000000000000000000000000000000000000");
    public static final PrivilegeBitSet USER_UPDATE =
            new PrivilegeBitSet("0000000000000000000000000010000000000000000000000000000000000000");
    public static final PrivilegeBitSet USER_DELETE =
            new PrivilegeBitSet("0000000000000000000000000001000000000000000000000000000000000000");

    public static final PrivilegeBitSet USER_GROUP_CREATE =
            new PrivilegeBitSet("0000000000000000000000000000100000000000000000000000000000000000");
    public static final PrivilegeBitSet USER_GROUP_READ =
            new PrivilegeBitSet("0000000000000000000000000000010000000000000000000000000000000000");
    public static final PrivilegeBitSet USER_GROUP_UPDATE =
            new PrivilegeBitSet("0000000000000000000000000000001000000000000000000000000000000000");
    public static final PrivilegeBitSet USER_GROUP_DELETE =
            new PrivilegeBitSet("0000000000000000000000000000000100000000000000000000000000000000");
}
