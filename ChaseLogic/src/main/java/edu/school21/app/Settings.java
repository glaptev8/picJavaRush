package edu.school21.app;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Settings {

  @Parameter(
    names = "--enemiesCount",
    description = "enemies Count",
    required = true
  )
  private Integer enemiesCount;

  @Parameter(
    names = "--wallsCount",
    description = "walls Count",
    required = true
  )
  private Integer wallsCount;

  @Parameter(
    names = "--size",
    description = "size map",
    required = true
  )
  private Integer size;


  @Parameter(
    names = "--profile",
    description = "profile",
    required = true
  )
  private String profile;


  public Integer getEnemiesCount() {
    return enemiesCount;
  }

  public Integer getWallsCount() {
    return wallsCount;
  }

  public Integer getSize() {
    return size;
  }

  public String getProfile() {
    return profile;
  }
}
