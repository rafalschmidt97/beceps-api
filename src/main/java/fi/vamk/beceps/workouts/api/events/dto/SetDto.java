package fi.vamk.beceps.workouts.api.events.dto;

import fi.vamk.beceps.workouts.domain.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SetDto {
  private Long id;
  private String name;
  private int setsAmount;
  private int repsAmount;

  public SetDto(Set set) {
    this.id = set.getId();
    this.name = set.getName();
    this.setsAmount = set.getSetsAmount();
    this.repsAmount = set.getRepsAmount();
  }
}
