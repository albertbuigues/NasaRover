So here is a first version of the NasaRover app. It only remains to implement the feature of executing the movements from a local json file. For now I've been tesrting the app with some buttons, as you can see here:

![Screenshot_20250502_194100](https://github.com/user-attachments/assets/53b1d418-a82d-48f7-adf5-0880828dea05)
![Screenshot_20250502_194115](https://github.com/user-attachments/assets/e9d59a0b-2ae5-40b1-9dc0-0a1e14ddad99)

All the UI is built with Jetpack Compose, with animations and added sounds for rotation movements, using Launched Effect and DisposableEffect.
The architecture is based in Model-View-ViewModel and the viewmodels are injected with Koin Dependency Injection System.
The unit tests have been done with JUnit4. If I have time I will introduce in the project some Espresso UI tests.

There is no limit for the number of rows and columns but I think I will put it because if not the UI can be a bit rude. 
First day I hade some troubles loading the image with Coil API because of the transparent background of the png so I opted to put it into the app assets.

Once I finish the task if I have time I will generate the apk and put it inside my Drive.

Here the link to the apk: 

Note: I've never done CD/CI actions (it would be nice to learn to use them but if I have time i will try to use Github Actions).

[ STEP 2: Load a local JSON file and position and move the robot ]

So in order to do this i've created an interface that would serve to read from all possible sources and map to all possible models.
And a concrete implementation has been done to map to NasaInstructions class and from a local file URI.
Coroutines have been used to read from files and to execute the movement steps.
Also I've used Mockk and Coroutines-test to test the method from my concrete implementation.
To prevent possible errors Excepcion Handling has been implemented.
