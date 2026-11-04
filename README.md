
# Central Asian String Instruments Tuner

An Android application for real-time tuning of traditional Central Asian string instruments.

**Developer:** Hamza Noyobshoev  
**University:** University of Central Asia (UCA)  
**Project:** Independent BSc Computer Science Final-Year Project, 2023

## Overview

Central Asian String Instruments Tuner is a Java-based Android application designed to help musicians tune traditional Central Asian string instruments.

The application uses real-time audio processing to detect musical pitch through the device's microphone and provides visual feedback to guide the tuning process.

The project was independently designed and developed as part of my Bachelor of Science in Computer Science at the University of Central Asia.

## Supported Instruments

- Pamiri Rubab
- Afghan Rubab
- Kyrgyz Komuz
- Guitar

Each instrument has a dedicated tuning interface with instrument-specific string selection and target frequencies.

## Technologies Used

- **Java** — Android application development
- **Android SDK** — Mobile application framework
- **TarsosDSP** — Digital audio processing
- **FFT YIN** — Pitch estimation algorithm
- **Gradle** — Project build management

## Key Features

### Real-Time Pitch Detection

The application captures audio through the device's microphone and uses the FFT YIN algorithm, implemented through the TarsosDSP library, to estimate the frequency of a played string.

### Instrument-Specific Tuning

Users can select one of four supported instruments and choose the string they wish to tune.

### Visual Tuning Feedback

The application displays the detected frequency and provides visual indicators to help users determine whether a string requires adjustment.

### Multilingual Interface

The application includes language selection functionality to make it more accessible to musicians from different linguistic backgrounds.

### Help and Instructions

An integrated help section explains how to use the application and interpret the tuning indicators.

## How It Works

1. Select the desired musical instrument.
2. Choose the string to tune.
3. Allow microphone access.
4. Play the selected string.
5. The application analyzes the audio signal using FFT YIN.
6. The detected frequency is compared with the target frequency.
7. Adjust the string while observing the visual tuning feedback.

## Testing and Validation

The application was tested using real examples of all four supported instruments:

- Pamiri Rubab
- Afghan Rubab
- Kyrgyz Komuz
- Guitar

The application remains in practical use more than three years after its initial development. I continue to use it to tune my own Pamiri Rubab.

## Project Significance

This project combines software engineering, digital signal processing, and traditional Central Asian musical practices.

It demonstrates how computer science can be applied to practical challenges while supporting the accessibility of regional cultural traditions.

The project also demonstrates independent problem-solving, algorithm integration, Android application development, and real-world software testing.

## Future Improvements

Potential enhancements include:

- Improving language-switching reliability
- Enhancing pitch detection in noisy environments
- Supporting additional traditional instruments
- Introducing customizable tuning configurations
- Improving compatibility with newer Android versions

## Academic Context

**University:** University of Central Asia  
**Degree:** Bachelor of Science in Computer Science  
**Graduation:** 2023  

This application was developed independently as my undergraduate final-year project.

## Author

**Hamza Noyobshoev**  
BSc Computer Science,
University of Central Asia

GitHub: https://github.com/Hamza0012340

## License

No open-source license has been specified for this repository.
