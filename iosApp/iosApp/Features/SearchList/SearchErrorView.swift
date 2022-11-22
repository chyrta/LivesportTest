//
//  SearchErrorView.swift
//  iosApp
//
//  Created by Dzmitry Chyrta on 22.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import LivesportKit

struct SearchErrorView: View {
    var errorState: SearchContractSearchErrorState
    var onButtonClick: () -> Void
    
    var body: some View {
        let message: String = {
            switch errorState {
            case .invalidrequestparameters:
                return "error_state_invalid_parameters"
            case .missingrequestparameters:
                return "error_state_missing_parameters"
            case .networkerror:
                return "error_state_network_error"
            case .serviceunavailable:
                return "error_state_service_unavailable"
            case .unknownerror:
                return "error_state_unknown_error"
            default:
                return "error_state_unknown_error"
            }
        }()
        
            
        
        VStack {
            Text("error".localized)
            Text(message.localized)
                .multilineTextAlignment(.center)
            Button("refresh".localized, action: onButtonClick)
        }
    }
}

struct SearchErrorView_Previews: PreviewProvider {
    static var previews: some View {
        SearchErrorView(errorState: SearchContractSearchErrorState.unknownerror, onButtonClick: {})
    }
}
