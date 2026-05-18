# Cat App

## Problem description

Create an app that loads cat breed data from a 3rd party API and display it as a list using modern Android practices.  

## Technical Choices

### AI

Due to time constraints and not having done any Android development, I would say Claude wrote most of this app and I mostly reviewed it. However, I don't have much of an issue with its architecture choices. Pure data is separated from data access. The viewmodel is separate from that, which in turn is separate from the actual views.  

### State

Complete data objects are passed between the list and detail view. This state could be saved in SavedInstanceState instead, leading to a much smoother experience when re-foregrounding the app.

### Translations

I haven't added the strings to the strings.xml file, like I would with fewer time constraints. However, since all names and descriptions come from an English only API, this shouldn't be an immediate concern: multi-language support would be a big feature, requiring some sort of translation service.

## More features

- Offline mode/caching: results could be cached in a (Room) database. This database could then be refreshed periodically or at the user's request. Since the data should be pretty stable, periodically would be a better choice.
- Search functionality: the API allows searching on (partial) names, which is a nice feature. However, if the above caching is added, searching on specific traits or description text could also be implemented.
- User settings: a choice between metric and imperial units
- Translations: since the api does not offer any support for other languages that I saw, this would require some sort of automated translations. This also conflicts with the hardcoded strings in the UI, as mentioned above.

## TODO

- An app icon that is properly scaled instead of the AI monstrosity this is in certain versions. The xxhdpi one is fine though.
- Find a better way to crop the images: some don't even have the cat's head in them.