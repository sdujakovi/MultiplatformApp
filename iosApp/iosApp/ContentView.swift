import SwiftUI
import shared

struct ContentView: View {
	let greet = "Stanko"

	var body: some View {
        ZStack{
            GithubRepositoriesView()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}