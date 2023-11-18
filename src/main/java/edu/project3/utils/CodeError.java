package edu.project3.utils;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public class CodeError {
    private final Map<Integer, String> errors = new HashMap<>();

    @SuppressWarnings("MagicNumber")
    public CodeError() {
        errors.put(100, "Continue");
        errors.put(101, "Switching Protocols");
        errors.put(102, "Processing (WebDAV; RFC 2518)");
        errors.put(103, "Early Hints (RFC 8297)");

        errors.put(200, "OK");
        errors.put(201, "Created");
        errors.put(202, "Accepted");
        errors.put(203, "Non-Authoritative Information");
        errors.put(204, "No Content");
        errors.put(205, "Reset Content");
        errors.put(206, "Partial Content");
        errors.put(207, "Multi-Status (WebDAV; RFC 4918)");
        errors.put(208, "Already Reported (WebDAV; RFC 5842)");
        errors.put(226, "IM Used (RFC 3229)");

        errors.put(300, "Multiple Choices");
        errors.put(301, "Moved Permanently");
        errors.put(302, "Found");
        errors.put(303, "See Other");
        errors.put(304, "Not Modified");
        errors.put(305, "Use Proxy");
        errors.put(306, "Switch Proxy");
        errors.put(307, "Temporary Redirect");
        errors.put(308, "Permanent Redirect");

        errors.put(400, "Bad Request");
        errors.put(401, "Unauthorized");
        errors.put(402, "Payment Required");
        errors.put(403, "Forbidden");
        errors.put(404, "Not Found");
        errors.put(405, "Method Not Allowed");
        errors.put(406, "Not Acceptable");
        errors.put(407, "Proxy Authentication Required");
        errors.put(408, "Request Timeout");
        errors.put(409, "Conflict");
        errors.put(410, "Gone");
        errors.put(411, "Length Required");
        errors.put(412, "Precondition Failed");
        errors.put(413, "Payload Too Large");
        errors.put(414, "URI Too Long");
        errors.put(415, "Unsupported Media Type");
        errors.put(416, "Range Not Satisfiable");
        errors.put(417, "Expectation Failed");
        errors.put(418, "I'm a teapot");
        errors.put(421, "Misdirected Request");
        errors.put(422, "Unprocessable Entity");
        errors.put(423, "Locked");
        errors.put(424, "Failed Dependency");
        errors.put(425, "Too Early");
        errors.put(426, "Upgrade Required");
        errors.put(428, "Precondition Required");
        errors.put(429, "Too Many Requests");
        errors.put(431, "Request Header Fields Too Large");
        errors.put(451, "Unavailable For Legal Reasons");

        errors.put(500, "Internal Server Error");
        errors.put(501, "Not Implemented");
        errors.put(502, "Bad Gateway");
        errors.put(503, "Service Unavailable");
        errors.put(504, "Gateway Timeout");
        errors.put(505, "HTTP Version Not Supported");
        errors.put(506, "Variant Also Negotiates");
        errors.put(507, "Insufficient Storage");
        errors.put(508, "Loop Detected");
        errors.put(510, "Not Extended");
        errors.put(511, "Network Authentication Required");
    }
}
