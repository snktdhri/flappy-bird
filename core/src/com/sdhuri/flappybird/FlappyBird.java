package com.sdhuri.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;


public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture [] birds;
	int flappstate = 0;
	int animate_counter = 0;
	int birdY = 0;
	int birdX = 0;
	float velocity = 0;
	int game_state = 0;
	int gravity = 1;
	Texture ttube;
	Texture btube;
	//int tubex = 0;
	int [] tubex;
	int [] offsets;
	Random random;
	float [] tube_factor;
	int numbmer_of_tubes = 4;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");
		birdX = Gdx.graphics.getWidth()/2 - (birds[flappstate].getWidth()/2);
		birdY = Gdx.graphics.getHeight()/2 - (birds[flappstate].getHeight()/2);
		ttube = new Texture("bottomtube.png");
		btube = new Texture("toptube.png");
		random = new Random();
		tubex = new int[numbmer_of_tubes];
		tube_factor = new float[numbmer_of_tubes];

		for (int i=0; i < numbmer_of_tubes ; i++){
			tubex[i] = Gdx.graphics.getWidth() + i * Gdx.graphics.getWidth()/2;
			tube_factor[i] = random.nextFloat() *2 -1;
		}

	}

	@Override
	public void render () {
		animate_counter++;

		if (animate_counter >= 5)
			animate_counter = 0;
		if (flappstate == 0 && animate_counter == 0)
			flappstate = 1;
		else if (flappstate == 1 && animate_counter == 0)
			flappstate = 0;



		if (game_state == 1){
			if (Gdx.input.justTouched()) {
				velocity = 20;
			}
			else {
				if (birdY > 0) {
					velocity -= gravity;

				}
				else
				{
					velocity = 0;
				}
			}
			birdY += velocity;

			for (int i=0; i < numbmer_of_tubes ; i++){
				tubex[i] -= 3;

			}

		}
		else
		{
			if (Gdx.input.justTouched()) {
				game_state = 1;
				velocity = 20;
			}
		}


		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(birds[flappstate], birdX, birdY);
		//batch.draw(ttube, Gdx.graphics.getWidth()/2 - ttube.getWidth()/2, Gdx.graphics.getHeight()- ttube.getHeight());
		for (int i=0; i < numbmer_of_tubes ; i++){
			batch.draw(btube, tubex[i] , Gdx.graphics.getHeight() - (int)(ttube.getHeight() * 0.50) + (int)(tube_factor[i] * Gdx.graphics.getHeight()  * 0.30));
			batch.draw(ttube, tubex[i], 0 - ((int)(ttube.getHeight() * 0.50)) + (int)(tube_factor[i]  * Gdx.graphics.getHeight() * 0.30));
		}

		//batch.draw(birds[flappstate], 0, 0);
		batch.end();
	}

}
