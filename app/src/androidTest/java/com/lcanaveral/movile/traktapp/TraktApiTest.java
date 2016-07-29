package com.lcanaveral.movile.traktapp;

import com.lcanaveral.movile.traktapp.api.Api;
import com.lcanaveral.movile.traktapp.api.payloads.ShowPayload;

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
        List<ShowPayload> shows = Api.getTrakt().getPopularShows();
        assertThat(shows.isEmpty(),is(true));
    }
}
