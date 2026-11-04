# Central Asian String Instruments Tuner

**An Android application for real-time tuning of traditional Central Asian string instruments.**

**Developer:** Hamza Noyobshoev  
**Institution:** University of Central Asia  
**Project:** Independent BSc Computer Science Final-Year Project (2023)

## Overview

Central Asian String Instruments Tuner is a Java-based Android application designed to assist musicians in tuning traditional string instruments using real-time audio processing.

The application supports four instruments:

- Pamiri Rubab
- Afghan Rubab
- Kyrgyz Komuz
- Guitar

The project combines digital signal processing, Android application development, and an interest in making technology accessible to musicians using traditional Central Asian instruments.

## Technologies

| Technology | Purpose |
|---|---|
| Java | Application development |
| Android | Mobile application platform |
| TarsosDSP | Audio processing and pitch detection |
| FFT YIN | Pitch estimation algorithm |
| Gradle | Android build configuration |

## Key Features

### Real-Time Pitch Detection
Uses microphone input and the FFT YIN pitch estimation algorithm through TarsosDSP to detect the frequency of a played string.

### Instrument-Specific Tuning
Provides dedicated interfaces for four instruments. Users select the instrument and the string they wish to tune.

### Visual Feedback
Displays the detected frequency, musical notes, and a visual tuning indicator. The tuning arc changes from red to green when the string reaches the target tuning range.

### String Warning
Includes a warning mechanism intended to alert users when the selected string may be incorrect or excessive tension may occur.

### Multilingual Interface
Provides language selection options to improve accessibility for musicians from different linguistic backgrounds.

### User Guidance
Includes a help section explaining how to select instruments, choose strings, and interpret the tuning indicators.

## How It Works

1. The user selects one of the four supported instruments.
2. The user selects the string to tune.
3. The application displays the target note and frequency.
4. The microphone captures audio from the played string.
5. TarsosDSP processes the audio and estimates its pitch using FFT YIN.
6. The application displays the detected pitch and provides visual feedback.
7. The user adjusts the string until the application indicates that it is in tune.

## Testing and Validation

The application was tested with physical examples of all four supported instruments.

The developer continues to use the application to tune a Pamiri Rubab, demonstrating its practical utility beyond the original university project.

## Known Limitations

- Language switching does not always function consistently.
- Pitch detection may be less reliable in noisy environments or when instruments produce complex harmonics.
- The application requires microphone permission to process audio.

## Future Improvements

- Improve multilingual interface reliability.
- Evaluate noise reduction and alternative pitch estimation methods.
- Expand support for additional regional instruments and tuning variations.
- Improve compatibility with newer Android versions.

## Academic Background

This project was independently designed and developed as part of the Bachelor of Science in Computer Science program at the University of Central Asia.

It demonstrates the practical application of software engineering and digital signal processing to a culturally relevant problem.

## Author

**Hamza Noyobshoev**  
BSc Computer Science,
University of Central Asia, 2023

GitHub: https://github.com/Hamza0012340

## License

No license has been specified. All rights remain with the copyright holder unless a license is added.
