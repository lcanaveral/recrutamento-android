package com.lcanaveral.movile.traktapp;

import android.test.suitebuilder.annotation.SmallTest;

import com.lcanaveral.movile.traktapp.api.Api;
import com.lcanaveral.movile.traktapp.api.payload.Show;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

/**
 * Created by lcanaveral on 7/29/16.
 */

@RunWith(MockitoJUnitRunner.class)

public class TraktApiTest {


    @Before
    public void createLogHistory() {

    }

    @Test
    private void trackApi_getPopularShows(){
        List<Show> shows = Api.getTrakt().getPopularShows();
        assertThat(shows.isEmpty(),is(true));
    }
}
