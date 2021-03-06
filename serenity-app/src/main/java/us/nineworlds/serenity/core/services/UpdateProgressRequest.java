package us.nineworlds.serenity.core.services;

import android.os.AsyncTask;
import javax.inject.Inject;
import us.nineworlds.serenity.common.injection.SerenityObjectGraph;
import us.nineworlds.serenity.common.rest.SerenityClient;
import us.nineworlds.serenity.core.model.VideoContentInfo;

public class UpdateProgressRequest extends AsyncTask<Void, Void, Void> {

  private final long position;
  private final VideoContentInfo video;

  @Inject SerenityClient factory;

  public UpdateProgressRequest(long position, VideoContentInfo video) {
    this.position = position;
    this.video = video;
    SerenityObjectGraph.Companion.getInstance().inject(this);
  }

  @Override protected Void doInBackground(Void... params) {
    final String id = video.id();
    if (video.isWatched()) {
      factory.setWatched(id);
      factory.setProgress("0", id);
    } else {
      factory.setProgress(id, Long.valueOf(position).toString());
    }
    return null;
  }
}