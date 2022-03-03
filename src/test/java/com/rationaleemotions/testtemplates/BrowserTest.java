package com.rationaleemotions.testtemplates;

import static com.rationaleemotions.testtemplates.BrowserTest.OS.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Locale;
import org.junit.platform.commons.util.StringUtils;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BrowserTest {

  Flavor flavor() default Flavor.FIREFOX;

  enum Flavor {
    FIREFOX(ANY),
    CHROME(ANY),
    IE(WINDOWS),
    SAFARI(MAC);

    Flavor(OS platform) {
      this.os = platform;
    }

    private final OS os;

    public OS getPlatform() {
      return os;
    }
  }

  enum OS {

    /**
     * IBM AIX operating system.
     */
    AIX,

    /**
     * Linux-based operating system.
     */
    LINUX,

    /**
     * Apple Macintosh operating system (e.g., macOS).
     */
    MAC,

    /**
     * Oracle Solaris operating system.
     */
    SOLARIS,

    /**
     * Microsoft Windows operating system.
     */
    WINDOWS,

    /**
     * An operating system other than {@link #AIX}, {@link #LINUX}, {@link #MAC}, {@link #SOLARIS},
     * or {@link #WINDOWS}.
     */
    ANY;

    private static final OS CURRENT_OS = determineCurrentOs();

    public static OS determineCurrentOs() {
      return parse(System.getProperty("os.name"));
    }

    static OS parse(String osName) {
      if (StringUtils.isBlank(osName)) {
        // null signals that the current OS is "unknown"
        return null;
      }

      osName = osName.toLowerCase(Locale.ENGLISH);

      if (osName.contains("aix")) {
        return AIX;
      }
      if (osName.contains("linux")) {
        return LINUX;
      }
      if (osName.contains("mac")) {
        return MAC;
      }
      if (osName.contains("sunos") || osName.contains("solaris")) {
        return SOLARIS;
      }
      if (osName.contains("win")) {
        return WINDOWS;
      }
      return ANY;
    }

    /**
     * @return {@code true} if <em>this</em> {@code OS} is known to be the operating system on which
     * the current JVM is executing
     */
    public boolean isCurrentOs() {
      return this == CURRENT_OS || this == ANY;
    }

  }
}
