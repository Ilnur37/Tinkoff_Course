package edu.project3.utils;

import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("MagicNumber")
public class CodeError {
    public static final Map<Integer, String> ERRORS = new HashMap<>();

    static {
        ERRORS.put(100, "Continue");
        ERRORS.put(101, "Switching Protocols");
        ERRORS.put(102, "Processing (WebDAV; RFC 2518)");
        ERRORS.put(103, "Early Hints (RFC 8297)");

        ERRORS.put(200, "OK");
        ERRORS.put(201, "Created");
        ERRORS.put(202, "Accepted");
        ERRORS.put(203, "Non-Authoritative Information");
        ERRORS.put(204, "No Content");
        ERRORS.put(205, "Reset Content");
        ERRORS.put(206, "Partial Content");
        ERRORS.put(207, "Multi-Status (WebDAV; RFC 4918)");
        ERRORS.put(208, "Already Reported (WebDAV; RFC 5842)");
        ERRORS.put(226, "IM Used (RFC 3229)");

        ERRORS.put(300, "Multiple Choices");
        ERRORS.put(301, "Moved Permanently");
        ERRORS.put(302, "Found");
        ERRORS.put(303, "See Other");
        ERRORS.put(304, "Not Modified");
        ERRORS.put(305, "Use Proxy");
        ERRORS.put(306, "Switch Proxy");
        ERRORS.put(307, "Temporary Redirect");
        ERRORS.put(308, "Permanent Redirect");

        ERRORS.put(400, "Bad Request");
        ERRORS.put(401, "Unauthorized");
        ERRORS.put(402, "Payment Required");
        ERRORS.put(403, "Forbidden");
        ERRORS.put(404, "Not Found");
        ERRORS.put(405, "Method Not Allowed");
        ERRORS.put(406, "Not Acceptable");
        ERRORS.put(407, "Proxy Authentication Required");
        ERRORS.put(408, "Request Timeout");
        ERRORS.put(409, "Conflict");
        ERRORS.put(410, "Gone");
        ERRORS.put(411, "Length Required");
        ERRORS.put(412, "Precondition Failed");
        ERRORS.put(413, "Payload Too Large");
        ERRORS.put(414, "URI Too Long");
        ERRORS.put(415, "Unsupported Media Type");
        ERRORS.put(416, "Range Not Satisfiable");
        ERRORS.put(417, "Expectation Failed");
        ERRORS.put(418, "I'm a teapot");
        ERRORS.put(421, "Misdirected Request");
        ERRORS.put(422, "Unprocessable Entity");
        ERRORS.put(423, "Locked");
        ERRORS.put(424, "Failed Dependency");
        ERRORS.put(425, "Too Early");
        ERRORS.put(426, "Upgrade Required");
        ERRORS.put(428, "Precondition Required");
        ERRORS.put(429, "Too Many Requests");
        ERRORS.put(431, "Request Header Fields Too Large");
        ERRORS.put(451, "Unavailable For Legal Reasons");

        ERRORS.put(500, "Internal Server Error");
        ERRORS.put(501, "Not Implemented");
        ERRORS.put(502, "Bad Gateway");
        ERRORS.put(503, "Service Unavailable");
        ERRORS.put(504, "Gateway Timeout");
        ERRORS.put(505, "HTTP Version Not Supported");
        ERRORS.put(506, "Variant Also Negotiates");
        ERRORS.put(507, "Insufficient Storage");
        ERRORS.put(508, "Loop Detected");
        ERRORS.put(510, "Not Extended");
        ERRORS.put(511, "Network Authentication Required");
    }
}
