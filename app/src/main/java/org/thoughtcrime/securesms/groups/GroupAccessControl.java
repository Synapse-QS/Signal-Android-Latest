package org.thoughtcrime.securesms.groups;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import org.thoughtcrime.securesms.R;
import org.thoughtcrime.securesms.database.GroupTable;

public enum GroupAccessControl {
  ALL_MEMBERS(R.string.GroupManagement_access_level_all_members),
  ONLY_ADMINS(R.string.GroupManagement_access_level_only_admins),
  ONLY_FOUNDER(R.string.GroupManagement_access_level_only_founder),
  NO_ONE(R.string.GroupManagement_access_level_no_one);

  private final @StringRes int string;

  GroupAccessControl(@StringRes int string) {
    this.string = string;
  }

  public @StringRes int getString() {
    return string;
  }

  /**
   * Returns true if the given [memberLevel] meets this access requirement.
   */
  public boolean allows(@NonNull GroupTable.MemberLevel memberLevel) {
    return switch (this) {
      case ALL_MEMBERS -> memberLevel.isInGroup();
      case ONLY_ADMINS -> memberLevel == GroupTable.MemberLevel.ADMINISTRATOR;
      case ONLY_FOUNDER -> memberLevel == GroupTable.MemberLevel.FOUNDER;
      case NO_ONE -> false;
    };
  }
}
